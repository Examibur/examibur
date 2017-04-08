# Build the latest integration image...
docker build -t examibur/ui_tests -f docker/Dockerfile.integration docker/

# Create named volumes (for performance)
docker volume create --name xamibur_int_build > /dev/null
docker volume create --name examibur_int_gradle > /dev/null
docker volume create --name examibur_int_userhome_gradle > /dev/null

# Run the image
REPO_BASE=$( cd "$( dirname "$0" )" && pwd )
docker run --rm -it \
    -v "${REPO_BASE}/examibur":/src/:rw \
    -v "${REPO_BASE}/examibur/screenshots/":/src/screenshots:rw \
    -v examibur_int_userhome_gradle:/home/examibur/.gradle/ \
    --net=host \
    -e UI_TEST_URL=http://localhost:8080/ \
    examibur/ui_tests
