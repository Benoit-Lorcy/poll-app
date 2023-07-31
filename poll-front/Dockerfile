# build stage
FROM node:lts-alpine as build-stage
WORKDIR /app
COPY package*.json ./
RUN npm install
COPY . .

# Append variables to .env because else well... it don't work..
ARG VITE_SITE_URL
ARG VITE_API_URL

RUN echo "VITE_SITE_URL=$VITE_SITE_URL" >> .env
RUN echo "VITE_API_URL=$VITE_API_URL" >> .env

RUN npm run build

# production stage
FROM nginx:stable-alpine as production-stage
COPY --from=build-stage /app/dist /usr/share/nginx/html
EXPOSE 80
CMD ["nginx", "-g", "daemon off;"]