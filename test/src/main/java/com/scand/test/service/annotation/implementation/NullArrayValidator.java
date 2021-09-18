package com.scand.test.service.annotation.implementation;

import com.scand.test.service.annotation.NullArray;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.List;

public class NullArrayValidator implements ConstraintValidator<NullArray, List<Integer>>
{
    @Override
    public boolean isValid(List<Integer> integers, ConstraintValidatorContext constraintValidatorContext)
    {
        if (integers.stream().allMatch(integer -> integer == null)) return false;
        else return true;
    }
}
