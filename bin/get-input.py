import requests
import sys

# python3 bin/get-input.py 1

def getInput(day):
    with open(".sec", "r") as file:
        input = requests.get(f"https://adventofcode.com/2020/day/{day}/input", headers = { "Cookie": file.read()}).text
    with open(f"./data/day{day}.txt", "w") as file:
        if input[-1] == "\n":
            input = input[0:len(input) - 1]
        file.write(input)
    return input
print(getInput(sys.argv[1]))

