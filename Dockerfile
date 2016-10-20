FROM fabianopaes/alpine-gradle:latest
MAINTAINER Fabiano Paes
RUN rm -f /bin/sh && ln -s /bin/bash /bin/sh && \
mkdir /temp && \
  cd /temp && git clone https://github.com/fabianopaes/simscale-catalog-rest-api.git && \
  directory=$(ls /temp/) && cp -pr  /temp/$directory/* /app/ #&& rm -rf /temp/${diretory}/ && cd /app
WORKDIR /app
EXPOSE 9000
CMD ["gradle", "bootRun"]
