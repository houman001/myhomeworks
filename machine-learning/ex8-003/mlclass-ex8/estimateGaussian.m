function [mu sigma2] = estimateGaussian(X)
%ESTIMATEGAUSSIAN This function estimates the parameters of a 
%Gaussian distribution using the data in X
%   [mu sigma2] = estimateGaussian(X), 
%   The input X is the dataset with each n-dimensional data point in one row
%   The output is an n-dimensional vector mu, the mean of the data set
%   and the variances sigma^2, an n x 1 vector
% 

% Useful variables
[m, n] = size(X);

% You should return these values correctly
mu = zeros(n, 1);
sigma2 = zeros(n, 1);

% ====================== YOUR CODE HERE ======================
% Instructions: Compute the mean of the data and the variances
%               In particular, mu(i) should contain the mean of
%               the data for the i-th feature and sigma2(i)
%               should contain variance of the i-th feature.
%

%disp('X:'), disp(X(1:4,:));
for j = 1:n
    mu(j) = sum(X(:,j)) / m;
end
%disp('mu:'), disp(mu');

DX = zeros(m, n);
for i = 1:m
    DX(i,:) = X(i,:) - mu';
end
%disp('DX:'), disp(DX(1:4,:));

for j = 1:n
    sigma2(j) = DX(:,j)' * DX(:,j) / m;
end
%disp('sigma2:'), disp(sigma2');

% =============================================================

end
