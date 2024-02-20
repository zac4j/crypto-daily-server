#!/bin/bash

#
# Start script for Render
#

echo "Starting Meteor application"

APP_DIR=$RENDER_PROJECT_ROOT/app
export MONGO_HOST=$MONGO_HOST
export MONGO_PORT=$MONGO_PORT
export MONGO_URL="${MONGO_URL:-mongodb://$MONGO_HOST:$MONGO_PORT}"
export ROOT_URL="${ROOT_URL:-http://$RENDER_EXTERNAL_HOSTNAME}"


cd $APP_DIR