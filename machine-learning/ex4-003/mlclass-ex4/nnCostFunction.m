function [J grad] = nnCostFunction(nn_params, ...
                                   input_layer_size, ...
                                   hidden_layer_size, ...
                                   num_labels, ...
                                   X, y, lambda)
%NNCOSTFUNCTION Implements the neural network cost function for a two layer
%neural network which performs classification
%   [J grad] = NNCOSTFUNCTON(nn_params, hidden_layer_size, num_labels, ...
%   X, y, lambda) computes the cost and gradient of the neural network. The
%   parameters for the neural network are "unrolled" into the vector
%   nn_params and need to be converted back into the weight matrices. 
% 
%   The returned parameter grad should be a "unrolled" vector of the
%   partial derivatives of the neural network.
%

% Reshape nn_params back into the parameters Theta1 and Theta2, the weight matrices
% for our 2 layer neural network
Theta1_1 = reshape(nn_params(1:hidden_layer_size * (input_layer_size + 1)), ...
                 hidden_layer_size, (input_layer_size + 1));

Theta2_1 = reshape(nn_params((1 + (hidden_layer_size * (input_layer_size + 1))):end), ...
                 num_labels, (hidden_layer_size + 1));
% disp("Theta1_1:"), disp(Theta1_1);
% disp("Theta2_1:"), disp(Theta2_1);
% disp("X:"), disp(X);

% Setup some useful variables
m = size(X, 1);
X = [ones(m,1) X];
         
% You need to return the following variables correctly 
J = 0;
Theta1_grad = zeros(size(Theta1_1));
Theta2_grad = zeros(size(Theta2_1));

% ====================== YOUR CODE HERE ======================
% Instructions: You should complete the code by working through the
%               following parts.
%
% Part 1: Feedforward the neural network and return the cost in the
%         variable J. After implementing Part 1, you can verify that your
%         cost function computation is correct by verifying the cost
%         computed in ex4.m
%
% Part 2: Implement the backpropagation algorithm to compute the gradients
%         Theta1_grad and Theta2_grad. You should return the partial derivatives of
%         the cost function with respect to Theta1 and Theta2 in Theta1_grad and
%         Theta2_grad, respectively. After implementing Part 2, you can check
%         that your implementation is correct by running checkNNGradients
%
%         Note: The vector y passed into the function is a vector of labels
%               containing values from 1..K. You need to map this vector into a 
%               binary vector of 1's and 0's to be used with the neural network
%               cost function.
%
%         Hint: We recommend implementing backpropagation using a for-loop
%               over the training examples if you are implementing it for the 
%               first time.
%
% Part 3: Implement regularization with the cost function and gradients.
%
%         Hint: You can implement this around the code for
%               backpropagation. That is, you can compute the gradients for
%               the regularization separately and then add them to Theta1_grad
%               and Theta2_grad from Part 2.
%

A1 = X;
Z2 = A1 * Theta1_1';
A2 = sigmoid(Z2);
A2 = [ones(m, 1) A2];
Z3 = A2 * Theta2_1';
A3 = sigmoid(Z3);
yLabels = zeros(m, num_labels);
for i = 1:m
	yLabels(i,:) = 1:num_labels == y(i);
end
diff = A3 - yLabels;
% disp("yLabels:"), disp(yLabels);
% disp("A3:"), disp(A3);
% disp("Diff:"), disp(diff);

J = 0;
for k = 1:num_labels
	prediction = A3(:,k);
	cost = (-1 / m) * (yLabels(:,k)' * log(prediction) + (1 - yLabels(:,k))' * (log(1 - prediction)));
	J = J + cost;
end
% disp("Cost:"), disp(J);
Theta1 = Theta1_1(:,2:end);
Theta2 = Theta2_1(:,2:end);
term1 = 0;
term2 = 0;
for j = 1:hidden_layer_size
	% disp("Theta1 at row:"), disp(j), disp(Theta1(j,:));
	term1 = term1 + Theta1(j,:) * Theta1(j,:)';
end
for j = 1:num_labels
	% disp("Theta2 at row:"), disp(j), disp(Theta2(j,:));
	term2 = term2 + Theta2(j,:) * Theta2(j,:)';
end
J = J + (lambda / (2 * m)) * (term1 + term2);
% disp("Regularized Cost:"), disp(J);


delta_2 = zeros(num_labels,hidden_layer_size + 1);
% disp("Delta 2 size:"), disp(size(delta_2));
delta_1 = zeros(hidden_layer_size,input_layer_size + 1);
% disp("Delta 1 size:"), disp(size(delta_1));
for t = 1:m
	a_1 = A1(t,:);
	% disp("a_1 for t:"), disp(t), disp(a_1);
	z_2 = Z2(t,:);
	% disp("z_2 for t:"), disp(t), disp(z_2);
	a_2 = A2(t,:);
	% disp("a_2 for t:"), disp(t), disp(a_2);
	z_3 = Z3(t,:);
	% disp("z_3 for t:"), disp(t), disp(z_3);
	a_3 = A3(t,:);
	% disp("a_3 for t:"), disp(t), disp(a_3);
	sigma_3 = (a_3 .- yLabels(t,:))';
	% disp("Sigma_3 for t:"), disp(t), disp(sigma_3);
	sigma_2 = (Theta2' * sigma_3) .* sigmoidGradient(z_2)';
	% disp("Sigma_2 for t:"), disp(t), disp(sigma_2);
	delta_2 = delta_2 + sigma_3 * a_2;
	% disp("Size of sigma_3 * a_2:"), disp(sigma_3 * a_2);
	delta_1 = delta_1 + sigma_2 * a_1;
	% disp("Size of sigma_2 * a_1:"), disp(sigma_2 * a_1);
end
delta_1 = delta_1 + lambda * [zeros(hidden_layer_size,1) Theta1];
Theta1_grad = delta_1 ./ m;
% disp("Theta1_grad:"), disp(Theta1_grad);
delta_2 = delta_2 + lambda * [zeros(num_labels,1) Theta2];
Theta2_grad = delta_2 ./ m;
% disp("Theta2_grad:"), disp(Theta2_grad);

% -------------------------------------------------------------

% =========================================================================

% Unroll gradients
grad = [Theta1_grad(:) ; Theta2_grad(:)];


end
