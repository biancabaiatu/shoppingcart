#Shopping cart

I finalized the implementation of the given tasks and added some new functionalities to the
Shopping cart.

I added a new field to the ShoppingCart class, priceFormat, which based on the enum I implemented
gives the preferred format of the receipt's lines and gives an opportunity to further develop the receipt line
formatting. The format is set based on pre-programmed defaults.

As it was not mentioned, I chose to print the receipt as a whole with the items grouped, in the order they
were scanned. To explain: if the following items were to be scanned: "apple", "banana", "apple", the 
receipt would print: "apple x 2", "banana x 1".

I have changed the method used to check the equality between the output expected and the actual output. I chose to 
split the lines by the '\n' separator.

I have also added multiple tests in order to check different functionalities, like the ability to print the price
before the product on the receipt line, or the capability to calculate the total of the receipt correctly.



