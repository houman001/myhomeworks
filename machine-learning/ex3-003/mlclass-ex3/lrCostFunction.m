function [J, grad] = lrCostFunction(theta, X, y, lambda)
%LRCOSTFUNCTION Compute cost and gradient for logistic regression with 
%regularization
%   J = LRCOSTFUNCTION(theta, X, y, lambda) computes the cost of using
%   theta as the parameter for regularized logistic regression and the
%   gradient of the cost w.r.t. to the parameters. 

% Initialize some useful values
m = length(y); % number of training examples
n = length(theta) - 1;

% ====================== YOUR CODE HERE ======================
% Instructions: Compute the cost of a particular choice of theta.
%               You should set J to the cost.
%               Compute the partial derivatives and set grad to the partial
%               derivatives of the cost w.r.t. each parameter in theta
%
% Hint: The computation of the cost function and gradients can be
%       efficiently vectorized. For example, consider the computation
%
%           sigmoid(X * theta)
%
%       Each row of the resulting matrix will contain the value of the
%       prediction for that example. You can make use of this to vectorize
%       the cost function and gradient computations. 
%
% Hint: When computing the gradient of the regularized cost function, 
%       there're many possible vectorized solutions, but one solution
%       looks like:
%           grad = (unregularized gradient for logistic regression)
%           temp = theta; 
%           temp(1) = 0;   % because we don't add anything for j = 0  
%           grad = grad + YOUR_CODE_HERE (using the temp variable)
%

prediction = sigmoid(X * theta);
% disp("Prediction:"), disp(prediction'(1:8));
diff = prediction - y;
% disp("Diff:"), disp(diff'(1:8));
grad = (1 / m) * X' * diff;
% disp("Grad:"), disp(grad');
theta1n = theta;
theta1n(1) = 0;
grad = grad + (lambda / m) * theta1n;
% disp("Theta:"), disp(theta1n');
% disp("Normalized Grad:"), disp(grad');

J = (-1 / m) * (y' * log(prediction) + (1 - y)' * (log(1 - prediction)));
theta1n = theta(2:n + 1);
regJ = lambda / (2 * m) * theta1n' * theta1n;
J = J + regJ;
% disp("Cost:"), disp(J);


% =============================================================

grad = grad(:);

end
