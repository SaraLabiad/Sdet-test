.PHONY: up test package clean

up:
	docker compose up -d

test:
	mvn test

package:
	mvn clean package

coverage:
	mvn verify

clean:
	mvn clean
	rm -rf target
