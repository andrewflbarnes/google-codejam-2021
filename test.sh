#!/usr/bin/env bash

gcl_class=Solution
gcl_source=${gcl_class}.java

function runTests() {
    local location=$1

    cd ${location}

    printf "\033[32m===>\033[35m ${location}\033[0m\n"

    printf "Compiling..."
    javac ${gcl_source}
    printf "\033[32mdone\033[0m\n"

    testpacks=$(find . -type f -name "testdata*in")

    if [ -z "${testpacks}" ]
    then
        printf "\033[33mNo tests found\033[0m\n"
    fi

    for testpack_in in ${testpacks}
    do
        testpack=$(sed 's/.in//' <<< ${testpack_in})
        testpack_out=${testpack}.out

        printf "\033[36m${testpack}\033[0m..."

        if [ -r ${testpack_out} ]
        then
            if ! diff ${testpack_out} <(java ${gcl_class} < ${testpack_in})
            then
                printf "\033[31mFailed tests\033[0m\n"
            else
                printf "\033[32mpassed\033[0m\n"
            fi
        else
            printf "\033[33mran\033[0m\n"
            java ${gcl_class} < ${testpack_in}
        fi
    done
}

location=$1

if [ -z "${location}" ]
then
    locations=qualification/*
fi

for location in ${locations}
do
    runTests ${location}
done