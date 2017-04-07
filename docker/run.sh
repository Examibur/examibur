XVFB_WHD=${XVFB_WHD:-5120x2880x16}

# Start Xvfb
Xvfb :99 -ac -screen 0 $XVFB_WHD -nolisten tcp &
xvfb=$!

export DISPLAY=:99

cd /src/
./gradlew integrationTest

