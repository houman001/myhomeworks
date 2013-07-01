function [J, grad] = cofiCostFunc(params, Y, R, num_users, num_movies, ...
                                  num_features, lambda)
%COFICOSTFUNC Collaborative filtering cost function
%   [J, grad] = COFICOSTFUNC(params, Y, R, num_users, num_movies, ...
%   num_features, lambda) returns the cost and gradient for the
%   collaborative filtering problem.
%

% Unfold the U and W matrices from params
X = reshape(params(1:num_movies*num_features), num_movies, num_features);
Theta = reshape(params(num_movies*num_features+1:end), ...
                num_users, num_features);

            
% You need to return the following values correctly
J = 0;
X_grad = zeros(size(X));
Theta_grad = zeros(size(Theta));


% disp('X'), disp(X);
% disp('Theta'), disp(Theta);
% disp('Y'), disp(Y);

DX = X * Theta' - Y;
DXR = DX .* R;
% disp('DX'), disp(DX);
J = sum(sum(DXR .* DXR)) / 2;

reg_theta = sum(sum(Theta * Theta' .* eye(size(Theta, 1), size(Theta, 1)))) * lambda / 2;
reg_x = sum(sum(X * X' .* eye(size(X, 1), size(X, 1)))) * lambda / 2;
J = J + reg_theta + reg_x;

X_grad = DXR * Theta;
Theta_grad = DXR' * X;

X_grad = X_grad + lambda * X;
Theta_grad = Theta_grad + lambda * Theta;


% =============================================================

grad = [X_grad(:); Theta_grad(:)];

end
