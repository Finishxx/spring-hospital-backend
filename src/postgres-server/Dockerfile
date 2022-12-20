FROM postgres:15.1
COPY create-script.sql /docker-entrypoint-initdb.d/
COPY insert-script.sql /docker-entrypoint-initdb.d/
# inspiration from https://wkrzywiec.medium.com/database-in-a-docker-container-how-to-start-and-whats-it-about-5e3ceea77e50
# I hope that the enviroment variables need only to be set in docker-compose

