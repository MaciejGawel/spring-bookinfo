import logging
import os
import requests
import sys
import time

URL = os.getenv("BOOKINFO_URL", "http://localhost:8080")
SERVICES = ["/products", "/details", "/reviews"]
SLEEP_TIME = 5

logging.basicConfig(stream=sys.stdout, level=logging.INFO)


def get_resources(service):
  response = requests.get(URL + service)

  if response.status_code != 200:
    logging.warning("GET {} returned unexpected status code: {}".format(service, response.status_code))
    return

  logging.info("GET {} returned 200 OK".format(service))
  logging.debug(response.json())


def main():
  for service in SERVICES:
    try:
      get_resources(service)
    except requests.exceptions.RequestException as e:
      logging.error(e)


if __name__ == "__main__":
  while(True):
    main()

    time.sleep(SLEEP_TIME)
