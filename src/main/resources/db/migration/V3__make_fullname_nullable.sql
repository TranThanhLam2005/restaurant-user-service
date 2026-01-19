-- Remove NOT NULL constraint from fullname column to allow users to update it later
ALTER TABLE users ALTER COLUMN fullname DROP NOT NULL;
