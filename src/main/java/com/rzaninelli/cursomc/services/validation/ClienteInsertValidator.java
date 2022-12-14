package com.rzaninelli.cursomc.services.validation;

import com.rzaninelli.cursomc.domain.Cliente;
import com.rzaninelli.cursomc.domain.enums.TipoCliente;
import com.rzaninelli.cursomc.dto.ClienteNewDTO;
import com.rzaninelli.cursomc.repositories.ClienteRepository;
import com.rzaninelli.cursomc.resources.exceptions.FieldMessage;
import com.rzaninelli.cursomc.services.validation.utils.BR;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

public class ClienteInsertValidator implements ConstraintValidator<ClienteInsert, ClienteNewDTO> {

    @Autowired
    private ClienteRepository clienteRepository;

    @Override
    public void initialize(ClienteInsert constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(ClienteNewDTO objDto, ConstraintValidatorContext constraintValidatorContext) {

        List<FieldMessage> list = new ArrayList<>();
        //inclua os testes aqui, inserindo erros na lista

        if (objDto.getTipo().equals(TipoCliente.PESSOAFISICA.getCod()) && !BR.isValidCPF(objDto.getCpfOuCnpj())){
            list.add(new FieldMessage("cpfOuCnpj", "CPF inválido"));
        }

        if (objDto.getTipo().equals(TipoCliente.PESSOAJURIDICA.getCod()) && !BR.isValidCNPJ(objDto.getCpfOuCnpj())){
            list.add(new FieldMessage("cpfOuCnpj", "CNPJ inválido"));
        }

        Cliente aux = clienteRepository.findByEmail(objDto.getEmail());
        if (aux != null){
            list.add(new FieldMessage("email", "E-mail já existente"));
        }

        for (FieldMessage element : list) {
            constraintValidatorContext.disableDefaultConstraintViolation();
            constraintValidatorContext.buildConstraintViolationWithTemplate(element.getMessage()).addPropertyNode(element.getFieldName()).addConstraintViolation();
        }

        return list.isEmpty();
    }
}
