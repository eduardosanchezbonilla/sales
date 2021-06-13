FROM alpine:3.11.3 as base
RUN apk --no-cache add openjdk11 gettext --repository=http://dl-cdn.alpinelinux.org/alpine/edge/community msttcorefonts-installer fontconfig && update-ms-fonts && fc-cache -f

FROM base as development
EXPOSE 8080
EXPOSE 5008
CMD ["sh", "-c", "java ${JVM_OPTS} -jar ${APP_OPTS} /app.war"]
