FROM ubuntu:focal
# źródełka
ENV PYTHON_VERSION=3.10.4
ENV DEBIAN_FRONTEND=noninteractive
RUN ln -snf /usr/share/zoneinfo/Poland /etc/localtime && echo Poland > /etc/timezone
# utilsy
RUN apt-get update && \
    apt-get install -y --fix-missing \
    git \
    python3 \
    python3-pip \
    curl \
    libffi-dev \
    libbz2-dev \
    supervisor \
    cron \
    sqlite3

RUN pip install flask requests
RUN pip install python-dotenv
COPY ./core/run_api.sh /run_api.sh
#COPY ./set_cron_job.sh /set_cron_job.sh
#RUN chmod +x /set_cron_job.sh
COPY ./src/application /app
RUN chmod +x /run_api.sh