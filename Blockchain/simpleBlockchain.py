import hashlib

class dataBlock:

    def __init__(self, previous_block_hash, transaction_list):
        self.previous_block_hash = previous_block_hash
        self.transactiion_list = transaction_list

        self.block_data = " | ".join(transaction_list) + " | " + previous_block_hash
        self.block_hash = hashlib.sha256(self.block_data.encode()).hexdigest()

t_list1 = "Alice sends 1 BTC to Bob"
t_list2 = "Bob sends 20 BTC to Charlie"
first_block = dataBlock("First Block", [t_list1, t_list2])
print(first_block.block_data)
print(first_block.block_hash)

t_list3 = "Cat sends 2 BTC to Dog"
t_list4 = "Dog sends 2 BTC to Eagle"
second_block = dataBlock("" + first_block.block_hash, [t_list3, t_list4])
print(second_block.block_data)
print(second_block.block_hash)

# print(hashlib.sha256('1234'.encode()).hexdigest())
