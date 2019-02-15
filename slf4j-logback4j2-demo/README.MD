# SLF4J (Simple Logging...)
- Abstracts various logging frameworks
- others: apache-commons-logging
- [manual](https://www.slf4j.org/manual.html)
- insert logging impl at runtime
- defaults to no op if no framework provided

# log4j or log4j2
- actual backend implementation of logging
- generic API for forward compatibility
- plugin pattern
- external property support
- lambda exp.
 - avoids using if(LOGLEVEL IS ENABLED) // build complex message
  - to save processing
  - log level evaluated b4 running lambda
 
 # Log4j 2 SLF4J Binding
 - SLF4J API -> Log4j 2 Implementation
 # Log4j to SLF4J Adapter
 - Log4j 2 API -> SLF4J -> SomeFramework