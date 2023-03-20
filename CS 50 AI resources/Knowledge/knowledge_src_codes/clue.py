import termcolor

from logic import *

mustard = Symbol("ColMustard")
plum = Symbol("ProfPlum")
scarlet = Symbol("MsScarlet")
characters = [mustard, plum, scarlet]

ballroom = Symbol("ballroom")
kitchen = Symbol("kitchen")
library = Symbol("library")
rooms = [ballroom, kitchen, library]

knife = Symbol("knife")
revolver = Symbol("revolver")
wrench = Symbol("wrench")
weapons = [knife, revolver, wrench]

symbols = characters + rooms + weapons

#takes my knowledge and try and draw conclusions about what I know
def check_knowledge(knowledge):
    for symbol in symbols:
        if model_check(knowledge, symbol):
            termcolor.cprint(f"{symbol}: YES", "green") #I am sure, symbol is true
        elif not model_check(knowledge, Not(symbol)): #if it is not true, then run model_check for not(symbol)
            print(f"{symbol}: MAYBE")   #I am not about whether symbol is true or false


# There must be a person, room, and weapon.
knowledge = And(
    Or(mustard, plum, scarlet),
    Or(ballroom, kitchen, library),
    Or(knife, revolver, wrench)
)

print("\nBefore initial cards")
check_knowledge(knowledge)

# Initial cards
knowledge.add(And(
    Not(mustard), Not(kitchen), Not(revolver)
))

print("\nAfter adding initial cards")
check_knowledge(knowledge)

# Unknown card
knowledge.add(Or(
    Not(scarlet), Not(library), Not(wrench) #someone cncluded at least one of them is true
))

print("\nAfter unknown card")
check_knowledge(knowledge)

# Known cards
knowledge.add(Not(plum))
knowledge.add(Not(ballroom))

print("\nAfter adding known cards")
check_knowledge(knowledge)
