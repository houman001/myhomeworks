function idx = findClosestCentroids(X, centroids)
%FINDCLOSESTCENTROIDS computes the centroid memberships for every example
%   idx = FINDCLOSESTCENTROIDS (X, centroids) returns the closest centroids
%   in idx for a dataset X where each row is a single example. idx = m x 1 
%   vector of centroid assignments (i.e. each entry in range [1..K])
%

% Set K
K = size(centroids, 1);

% You need to return the following variables correctly.
idx = zeros(size(X,1), 1);

% ====================== YOUR CODE HERE ======================
% Instructions: Go over every example, find its closest centroid, and store
%               the index inside idx at the appropriate location.
%               Concretely, idx(i) should contain the index of the centroid
%               closest to example i. Hence, it should be a value in the 
%               range 1..K
%
% Note: You can use a for-loop over the examples to compute this.
%

m = size(X, 1);
n = size(X, 2);
%fprintf('size of X: %dx%d, K: %d\n', m, n, K);
%disp('X'), disp(X(1:8,:));
%disp('centroids'), disp(centroids);
dist = zeros(m, K);
for i = 1 : m
    %fprintf('i: %d\n', i);
    dv = zeros(K, 1);
    for k = 1 : K
        %fprintf('k: %d\n', k);
        %disp(X(i, :));
        %disp(centroids(k, :));
        %disp('Diff vector:'), (disp(X(i, :) - centroids(k, :))); 
        dv(k) = (X(i, :) - centroids(k, :))* (X(i, :) - centroids(k, :))';
        %fprintf('dv(%d): %d\n', k, dv(k));
    end;
    %disp('dv'), disp(dv);
    [minDist,minIdx] = min(dv);
    %fprintf('min: %d at %d\n', minDist, minIdx);
    idx(i) = minIdx;
end;

% =============================================================

end

