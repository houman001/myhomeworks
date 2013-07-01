function J = computeCost(X, y, theta)
%COMPUTECOST Compute cost for linear regression
%   J = COMPUTECOST(X, y, theta) computes the cost of using theta as the
%   parameter for linear regression to fit the data points in X and y

% Initialize some useful values
m = length(y); % number of training examples

% You need to return the following variables correctly 
% J = 0;

% ====================== YOUR CODE HERE ======================
% Instructions: Compute the cost of a particular choice of theta
%               You should set J to the cost.

% disp("X:"), disp(X'(1:2,1:4));
% disp("Theta:"), disp(theta');
% disp("y:"), disp(y'(1:4));
prediction = X * theta;
% disp("Prediction:"), disp(prediction'(1:4));
diff = prediction - y;
% disp("Diff:"), disp(diff'(1:4));
J = diff' * diff / (2 * m);

% =========================================================================

end
