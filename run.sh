#!/usr/bin/env bash

gcj_class=Solution
gcj_source=${gcj_class}.java
gcj_default_locations=qualification

project_dir=$(pwd)

function runTests() {
    local location=$1

    cd ${location}

    printf "\033[32m===>\033[35m ${location}\033[0m\n"

    printf "Compiling..."
    if ! javac ${gcj_source}
    then
        printf "\033[31mCompilation failed\033[0m\n"
        return
    fi
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
            if ! diff ${testpack_out} <(java ${gcj_class} < ${testpack_in})
            then
                printf "\033[31mFailed tests\033[0m\n"
            else
                printf "\033[32mpassed\033[0m\n"
            fi
        else
            printf "\033[33mran\033[0m\n"
            java ${gcj_class} < ${testpack_in}
        fi
    done

    cd ${project_dir}
}

locations=$@

if [ -z "${locations}" ]
then
    locations=${gcj_default_locations}
fi

check_locations=${locations}
locations=""
for location in ${check_locations}
do
    found_locations=$(find $location -type f -name ${gcj_source})
    for found in ${found_locations}
    do
        locations="${locations} $(dirname ${found})"
    done
done

for location in ${locations}
do
    runTests ${location}
done
