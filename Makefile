.PHONY: build

all: build

build:
	lein uberjar
	cp target/*-standalone.jar /home/developer/Downloads/neo4j-community-2.1.5/lib
