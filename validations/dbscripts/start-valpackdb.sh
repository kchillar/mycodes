docker run --rm   --name ValpackDB -e POSTGRES_PASSWORD=postgres -d -p 5432:5432 -v $HOME/postgres/valpackdb:/var/lib/postgresql/data  postgres
