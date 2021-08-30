package com.scand.test.services.annotations.implementations;

import com.scand.test.services.annotations.NullArray;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.ArrayList;

public class NullArrayValidator implements ConstraintValidator<NullArray, ArrayList<Integer>>
{
    @Override
    public boolean isValid(ArrayList<Integer> integers, ConstraintValidatorContext constraintValidatorContext)
    {
        if (integers.stream().allMatch(integer -> integer == null)) return false;
        else return true;
    }
}
