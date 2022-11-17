#This is a sample Image 
FROM ubuntu 
MAINTAINER abhishek.kumar.cse09@itbhu.ac.in

RUN apt-get update 
RUN apt-get install –y nginx 
CMD [“echo”,”Image created”] 
