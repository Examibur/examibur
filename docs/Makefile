.PHONY: serve build default build-image

default: buildimage serve

buildimage:
	docker build -t engineering-projekt/examibur-website website/docker/

build:
	docker run -i -t --rm --name examibur-website -u jekyll -v $(shell pwd):/src/ -p 4000:4000 engineering-projekt/examibur-website jekyll build -d public

serve:
	docker run -i -t --rm --name examibur-website -u jekyll -v $(shell pwd):/src/ -p 4000:4000 engineering-projekt/examibur-website jekyll serve -d public
