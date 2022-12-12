package com.rzaninelli.cursomc.services.validation;

import com.rzaninelli.cursomc.domain.Cliente;
import com.rzaninelli.cursomc.domain.enums.TipoCliente;
import com.rzaninelli.cursomc.dto.ClienteDTO;
import com.rzaninelli.cursomc.dto.ClienteNewDTO;
import com.rzaninelli.cursomc.repositories.ClienteRepository;
import com.rzaninelli.cursomc.resources.exceptions.FieldMessage;
import com.rzaninelli.cursomc.services.validation.utils.BR;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerMapping;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ClienteUpdateValidator implements ConstraintValidator<ClienteUpdate, ClienteDTO> {

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private ClienteRepository clienteRepository;

    @Override
    public void initialize(ClienteUpdate constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(ClienteDTO objDto, ConstraintValidatorContext constraintValidatorContext) {

        Map<String, String> map = (Map<String, String>)request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
        Integer uriId = Integer.parseInt(map.get("id"));

        List<FieldMessage> list = new ArrayList<>();
        //inclua os testes aqui, inserindo erros na lista

        Cliente aux = clienteRepository.findByEmail(objDto.getEmail());
        if (aux != null && !aux.getId().equals(uriId)){
            list.add(new FieldMessage("email", "E-mail já existente"));
        }

        for (FieldMessage element : list) {
            constraintValidatorContext.disableDefaultConstraintViolation();
            constraintValidatorContext.buildConstraintViolationWithTemplate(element.getMessage()).addPropertyNode(element.getFieldName()).addConstraintViolation();
        }

        return list.isEmpty();
    }
}
