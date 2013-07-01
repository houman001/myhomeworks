function [X_norm, mu, sigma] = featureNormalize(X)
%FEATURENORMALIZE Normalizes the features in X 
%   FEATURENORMALIZE(X) returns a normalized version of X where
%   the mean value of each feature is 0 and the standard deviation
%   is 1. This is often a good preprocessing step to do when
%   working with learning algorithms.

% You need to set these values correctly
X_norm = X;
m = size(X, 1);
factors = size(X, 2);
mu = zeros(1, factors);
sigma = zeros(1, factors);

% ====================== YOUR CODE HERE ======================
% Instructions: First, for each feature dimension, compute the mean
%               of the feature and subtract it from the dataset,
%               storing the mean value in mu. Next, compute the 
%               standard deviation of each feature and divide
%               each feature by it's standard deviation, storing
%               the standard deviation in sigma. 
%
%               Note that X is a matrix where each column is a 
%               feature and each row is an example. You need 
%               to perform the normalization separately for 
%               each feature. 
%
% Hint: You might find the 'mean' and 'std' functions useful.
%       

mu = mean(X, 1);
disp("MU: "), disp(mu);
X_norm = X_norm - repmat(mu, m, 1);
% disp(X_norm);
% disp("Mean value:"), disp(mean(X, 1));
% disp("Number of columns:"), disp(factors)
for i = 1:factors
    % printf("Column %d:", i);
    % disp(std(X(:,i)));
    sigma(i) = std(X(:,i));
    X_norm(:,i) = X_norm(:,i) / sigma(i);
end
disp("SIGMA: "), disp(sigma);
disp("Normalized X: "), disp(X_norm);

% ============================================================

end
