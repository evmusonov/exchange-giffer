## Exchange Giffer
The service shows a gif image (a rate is up or down) depends on a currency rate using external APIs (https://docs.openexchangerates.org/ and https://developers.giphy.com/docs/api#quick-start-guide).

## Setup
Make sure you have Docker installed on your machine.

1. Clone the repository
2. In the project directory:
```
docker build --tag giffer ./
```
then
```
docker run -d -it -p 8080:8080 --name giffer giffer
```
3. Now you have the container running on the port 8080. Go to `http://localhost:8080` in a browser to see the result.

All application logs you can see in the `application.log` file.
