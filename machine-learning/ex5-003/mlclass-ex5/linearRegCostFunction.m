function [J, grad] = linearRegCostFunction(X, y, theta, lambda)
%LINEARREGCOSTFUNCTION Compute cost and gradient for regularized linear 
%regression with multiple variables
%   [J, grad] = LINEARREGCOSTFUNCTION(X, y, theta, lambda) computes the 
%   cost of using theta as the parameter for linear regression to fit the 
%   data points in X and y. Returns the cost in J and the gradient in grad

% Initialize some useful values
m = length(y); % number of training examples
n = length(theta) - 1;


% You need to return the following variables correctly 
J = 0;
grad = zeros(size(theta));

% ====================== YOUR CODE HERE ======================
% Instructions: Compute the cost and gradient of regularized linear 
%               regression for a particular choice of theta.
%
%               You should set J to the cost and grad to the gradient.
%
prediction = X * theta;
% disp("Prediction:"), disp(prediction'(1:4));
diff = prediction - y;
% disp("Diff:"), disp(diff'(1:4));

grad = (1 / m) * X' * diff;
%disp("Grad:"), disp(grad'(1:8));
theta1n = theta;
theta1n(1) = 0;
grad = grad + (lambda / m) * theta1n;
%disp("Theta:"), disp(theta1n'(1:8));
%disp("Normalized Grad:"), disp(grad'(1:8));

J = diff' * diff / (2 * m);
%theta1n = theta(2:n + 1);
regJ = lambda / (2 * m) * theta1n' * theta1n;
J = J + regJ;
%disp("Cost:"), disp(J);



% =========================================================================

grad = grad(:);

end
