package use_case.signup;

import use_case.signup.SignupInputData;

public interface SignupInputBoundary {
    void execute(SignupInputData signupInputData);
}
