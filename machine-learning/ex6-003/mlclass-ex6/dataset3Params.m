function [C, sigma] = dataset3Params(X, y, Xval, yval)
%EX6PARAMS returns your choice of C and sigma for Part 3 of the exercise
%where you select the optimal (C, sigma) learning parameters to use for SVM
%with RBF kernel
%   [C, sigma] = EX6PARAMS(X, y, Xval, yval) returns your choice of C and 
%   sigma. You should complete this function to return the optimal C and 
%   sigma based on a cross-validation set.
%

% You need to return the following variables correctly.
C = 1;
sigma = 0.3;

% ====================== YOUR CODE HERE ======================
% Instructions: Fill in this function to return the optimal C and sigma
%               learning parameters found using the cross validation set.
%               You can use svmPredict to predict the labels on the cross
%               validation set. For example, 
%                   predictions = svmPredict(model, Xval);
%               will return the predictions on the cross validation set.
%
%  Note: You can compute the prediction error using 
%        mean(double(predictions ~= yval))
%

C_vector = [0 0.001 0.003 0.01 0.03 0.1 0.3 1 3 10]';
sigma_vector = [0 0.001 0.003 0.01 0.03 0.1 0.3 1 3 10]';
minError = -1
for i = 1 : length(C_vector)
    for j = 1 : length(sigma_vector)
	C_test = C_vector(i);
        sigma_test = sigma_vector(j);
        % disp("C and sigma"), disp(C_test), disp(sigma_test);
        model= svmTrain(X, y, C_test, @(x1, x2) gaussianKernel(x1, x2, sigma_test));
        predictions = svmPredict(model, Xval);
        % disp(predictions(1:10)');
        % disp(yval(1:10)');
        err = mean(double(predictions ~= yval));
	if minError < 0 || err < minError
            minError = err;
	    C = C_test;
	    sigma = sigma_test;
            fprintf('C: %f, sigma: %f\n', C, sigma);
        end
    end;
end;






% =========================================================================

end
