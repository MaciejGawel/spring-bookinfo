FROM python:3.6.10-alpine

COPY requirements.txt .
RUN pip install -r requirements.txt

COPY client.py .

CMD [ "python", "./client.py" ]
