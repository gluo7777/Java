# Redis
- https://redis.io/	
- NoSQL in-memory cache and storage.
- mix of memory and disk storage that allows persistence in case of failure
- abstract data types
- query against API instead of query language
- fork: master process spawns child process that persists data while master continues serving client
- Append-only-file (AOF) for persistence. Automatic rewriting.
- 1 or 2 thread only. Cannot parallelize operations.

## VS RDBMS
- better performance because RDBMS need to write to disk before committing a transaction.
- decoupled from database-specific query language
- lacks parallelism

## Jedis
- https://github.com/xetorthio/jedis/wiki/Getting-started
- A single Jedis instance is not threadsafe!
- JedisPool