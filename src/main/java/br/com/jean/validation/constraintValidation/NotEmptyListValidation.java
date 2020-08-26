package br.com.jean.validation.constraintValidation;

import br.com.jean.validation.NotEmptyList;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.List;

public class NotEmptyListValidation implements ConstraintValidator<NotEmptyList, List> {


    @Override
    public boolean isValid(List value, ConstraintValidatorContext context) {
    return value != null && !value.isEmpty();
    }

    @Override
    public void initialize(NotEmptyList constraintAnnotation) {

    }

}
