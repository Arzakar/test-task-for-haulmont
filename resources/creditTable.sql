CREATE TABLE IF NOT EXISTS creditsTable (
	clientID VARCHAR(36),
	creditLimit FLOAT, 
	interestRate FLOAT, 
	creditTerm INTEGER,
	startDate DATE
);