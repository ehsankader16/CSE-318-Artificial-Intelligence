from model import model
#print("I'm before calling prediction")
# Calculate predictions
predictions = model.predict_proba({
    "train": "delayed"
})
#print("I'm after calling prediction")

# Print predictions for each node
for node, prediction in zip(model.states, predictions):
    if isinstance(prediction, str):
        print(f"{node.name}: {prediction}")
    else:
        print(f"{node.name}")
        for value, probability in prediction.parameters[0].items():
            print(f"    {value}: {probability:.4f}")
