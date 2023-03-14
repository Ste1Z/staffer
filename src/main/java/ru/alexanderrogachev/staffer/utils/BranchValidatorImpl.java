package ru.alexanderrogachev.staffer.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ru.alexanderrogachev.staffer.models.Branch;
import ru.alexanderrogachev.staffer.services.BranchServiceImpl;

@Component
public class BranchValidatorImpl implements Validator {

    private static final Logger logger = LogManager.getLogger(BranchValidatorImpl.class);

    private final BranchServiceImpl branchService;

    @Autowired
    public BranchValidatorImpl(BranchServiceImpl branchService) {
        this.branchService = branchService;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return Branch.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Branch branch = (Branch) target;
        if (branchService.findBranchByBranchName(branch.getBranchName()).isPresent()) {
            errors.rejectValue("branchName", "", "Данный филиал уже существует");
        }
        logger.info("[" + this.getClass().getSimpleName() + "]" + " Валидация филиала успешно пройдена");
    }
}
