function centroids = computeCentroids(X, idx, K)
%COMPUTECENTROIDS returs the new centroids by computing the means of the 
%data points assigned to each centroid.
%   centroids = COMPUTECENTROIDS(X, idx, K) returns the new centroids by 
%   computing the means of the data points assigned to each centroid. It is
%   given a dataset X where each row is a single data point, a vector
%   idx of centroid assignments (i.e. each entry in range [1..K]) for each
%   example, and K, the number of centroids. You should return a matrix
%   centroids, where each row of centroids is the mean of the data points
%   assigned to it.
%

% Useful variables
[m n] = size(X);

% You need to return the following variables correctly.
centroids = zeros(K, n);


% ====================== YOUR CODE HERE ======================
% Instructions: Go over every centroid and compute mean of all points that
%               belong to it. Concretely, the row vector centroids(i, :)
%               should contain the mean of the data points assigned to
%               centroid i.
%
% Note: You can use a for-loop over the centroids to compute this.
%

fprintf('size(X)=%dx%d\n', m, n);
fprintf('size(idx)=%dx%d\n', size(idx,1), size(idx,2));
fprintf('size(centroids)=%dx%d\n', size(centroids,1), size(centroids,2));
for k = 1:K
    %fprintf('k=%d\n', k);
    %fprintf('indexes matching=%d\n', size(X(idx==k,:),1));
    if (size(X(idx==k,:),1) == 0)
        %fprintf('No entry found.\n');
        centroids(k,:) = zeros(1,n);
    else
        meanMatrix = mean(X(idx==k,:),1);
        %fprintf('size(mean)=%dx%d\n', size(meanMatrix,1), size(meanMatrix,2));
        centroids(k,:) = meanMatrix;
    end;
end;

