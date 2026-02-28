**SENG 637 - Software Testing, Reliability, and Quality**

**Lab. Report \#2 – Requirements-Based Test Generation**

| Group \#: 12     |
| ---------------- |
| Student Names:   |
| Jason Chiu       |
| William Watson   |
| Jack Shenfield   |
| Barrett Sapunjis |

# 1 Introduction

This lab focuses on applying black-box testing techniques and unit testing within the JUnit suite to test software modules against their requirements outlined in their documentation. Practical black-box tests were designed and performed against specific methods within a software system. The software system tested in this lab was JFreeChart, a free open-source Java library used for data visualization. The two classes tested were Range and DataUtilities, both part of the org.jfree.data package. After analyzing the domains and bounds of input variables into each method, a list of test cases was created.

The group followed JUnit’s standards, patterns, and naming conventions in the process of completing this lab. Specific black-box testing paradigms such as Equivalence Class Partitioning (Strong and Weak), Robustness, and Boundary Value Analysis were utilized throughout.

# 2 Detailed description of unit test strategy

## Objective
The goal of this unit test strategy is to outline the approach to black box unit testing that will be utilized on the selected classes of the JFreeChart framework:

- `org.jfree.data.DataUtilities` (all 5 existing methods)
- `org.jfree.data.Range` (5 selected methods: `combine`, `contains`, `getLength`, `shift`, `toString`)

Testing will focus on the behavior of methods as outlined in the Javadoc specifications. The following black-box techniques will be applied:

- Equivalence Class Partitioning (ECP)
- Boundary Value Analysis (BVA)
- Robustness Testing
- Along with additional considerations where they apply case by case

The objective is to systematically design test cases that ensure adequate coverage of valid, invalid, boundary, and interaction dependent inputs with a focus only on unit level functionality and testing.

## Approach
The testing approach relies on three primary black-box techniques:

### 1. Equivalence Class Partitioning (ECP)
For each method:

- All input variables are identified.
- The domain of each input is divided into equivalence classes.
- Each class represents a set of values expected to behave similarly.
- Representative values are selected from each class.

Two forms are applied where applicable:

A. Weak ECP
- Variables are tested independently.
- Other variables held at nominal valid values.
- Used for most methods.

B. Strong ECP
- Selected combinations of equivalence classes across multiple inputs are tested.
- Used where interaction between inputs affects behavior.

### 2. Boundary Value Analysis (BVA)
Boundary testing is applied to numeric and range-based inputs. The following boundary conditions are tested where applicable and useful:

- Lower bound
- Upper bound
- Just below lower bound
- Just above upper bound
- Zero values
- Negative values

### 3. Robustness Test
Robustness testing extends equivalence testing to invalid inputs such as:

- `null` references
- Empty arrays
- Negative indices
- Out-of-range indices
- Zero-length ranges

## Test Case Design
Test cases are designed by:

1. Identifying input variables.
2. Determining valid and invalid domains.
3. Creating equivalence classes.
4. Identifying boundary values.
5. Selecting representative values.
6. Combining partitions selectively when interaction affects behavior.
7. Defining expected results strictly according to Javadoc.

Each test case is mapped to:

- A source method
- A specific partition
- A testing technique
- An expected outcome

Through this way, it should be clear how test cases were defined and how they are able to encapsulate the domains necessary to capture full unit testing of the methods.

### Table 1: Test Method Analysis for 5 Selected Range Class Methods

| Source Method | Input Variable(s) | Domain | Equivalence Classes (EC) | Boundaries Tested |
|---------------|-----------------|--------|--------------------------|-----------------|
| shift | base, delta, allowZeroCrossing | base ∈ Range ∪ {null}, delta ∈ ℝ, allowZeroCrossing ∈ {true,false} | EC1: base=null <br> EC2: delta=0 <br> EC3: delta>0 <br> EC4: delta<0 <br> EC5: delta=Double.MAX_VALUE <br> EC6: negative delta with zero crossing prevented | base=null, delta=0, delta max, negative delta crossing zero |
| toString | lower, upper | lower ≤ upper | EC1: positive range <br> EC2: negative range <br> EC3: zero-length range | negative bounds, zero-length range |
| combine | r1, r2 | r1, r2 ∈ Range ∪ {null} | EC1: r1=null, r2=null <br> EC2: r1!=null, r2=null <br> EC3: r1=null, r2!=null <br> EC4: r1,r2 non-null: overlapping, disjoint, contained | overlapping ranges, disjoint ranges, containment |
| contains | value, lower, upper | lower ≤ upper, value ∈ ℝ | EC1: lower < value < upper <br> EC2: value = lower <br> EC3: value = upper <br> EC4: value < lower <br> EC5: value > upper | value = lower, value = upper, value < lower, value > upper |
| getLength | lower, upper | lower ≤ upper | EC1: upper > lower <br> EC2: upper = lower <br> EC3: both bounds negative | positive length, zero-length, negative bounds |

### Table 2: Test Method Analysis for DataUtilities Class Methods

| Source Method | Input Variable(s) | Domain | Equivalence Classes (EC) | Boundaries Tested |
|---------------|-----------------|--------|--------------------------|-----------------|
| calculateColumnTotal | data, column | data ∈ Values2D, column ∈ [0..n-1] | EC1: null dataset <br> EC2: empty table <br> EC3: single row <br> EC4: multiple rows <br> EC5: all null cells <br> EC6: negative column index <br> EC7: column index out of bounds | empty table, single row, multiple rows, invalid column, all null cells, negative column index |
| calculateRowTotal | data, row | data ∈ Values2D, row ∈ [0..n-1] | EC1: null dataset <br> EC2: empty table <br> EC3: single column <br> EC4: multiple columns <br> EC5: all null cells <br> EC6: negative row index <br> EC7: row index out of bounds | empty table, single column, multiple columns, invalid row, all null cells, negative row index |
| createNumberArray | array | array ∈ double[] ∪ {null} | EC1: null input <br> EC2: empty array <br> EC3: single element <br> EC4: multiple elements | size = 0, size = 1, size > 1 |
| createNumberArray2D | array | array ∈ double[][] ∪ {null} | EC1: null input <br> EC2: empty outer array <br> EC3: outer array with empty inner arrays <br> EC4: single element <br> EC5: 2D array with negative/zero/positive values | empty outer, empty inner, single element, negative/zero/positive values |
| getCumulativePercentages | data | data ∈ KeyedValues ∪ {null} | EC1: null input <br> EC2: empty dataset <br> EC3: single value <br> EC4: multiple positive values <br> EC5: multiple zero values <br> EC6: mix of positive and negative values <br> EC7: total sum zero | empty dataset, single value, multiple positives, multiple zeros, mixed positive/negative, zero total sum |

## Logistics
- Testing framework: JUnit 4
- Development environment: Eclipse (Java 8)
- One test class per source class:
  - `RangeTest`
  - `DataUtilitiesTest`
- Each test method:
  - Follows JUnit test code standards, patterns, and naming conventions
  - Includes comments referencing partition and technique
- Entire suite executable using:
  - Run As → JUnit Test

## Deliverables
This test strategy will deliver the following to show the results of the black box unit testing:

1. Eclipse project including:
   - All test classes
   - Required JFreeChart libraries
   - JUnit dependencies
2. Fully executable JUnit test suite

# 3 Test cases developed

This section presents the concrete JUnit test cases developed based on the unit test strategy described in Section 2. Each test case is derived directly from identified equivalence classes, boundary conditions, robustness considerations, and interaction-based scenarios. The tables below maps every test method to its corresponding source method, partition covered, testing technique applied, and expected outcome, ensuring traceability between the test strategy and its implementation.

The five methods from the JFreeChart Range class were selected because they collectively represent a diverse set of behaviours: object interaction (`combine`), simple boolean evaluation (`contains`), numerical computation (`getLength`), state transformation with conditional logic (`shift`), and string representation (`toString`). Together, these methods cover scalar inputs, object interactions, boundary sensitive logic, and special case handling (e.g., null parameters and zero crossing behaviour). This variety allows for meaningful application of equivalence class partitioning, boundary value analysis, robustness testing, and interaction-based testing within a manageable subset of the class’s functionality.

# Mocking in Testing

A benefit of using mocking is that it help isolate the SUT. By replacing external dependencies with mock versions, a smaller chunk of code can be independently tested. Any failure in the test would be due to the SUT and not a dependency. Additionally, Mocking helps reduce test execution speed. The testing suite does not have to wait for external services to be called and a result to come back. Assuming a lot of tests are being automated, this would reduce the execution time substantially.

A drawback of using mocking is that the mocked version may not perfectly imitate the real-life dependency. This could result in false positives in testing. When the SUT is connected to the actual SUT it may fail. On top of this, mocking increases test development time. A software tester has to spend time writing a mock, often many depending on the SUT. Also if any dependent modules are added later further mocks must be created. 

# Table 3: Range Class Test Cases

| Test Method Name | Source Method | Partition Covered | Technique Used | Expected Outcome |
|-----------------|---------------|-----------------|----------------|----------------|
| testShiftNullBase() | shift | base null | Robustness | Throw NullPointerException |
| testShiftZeroDelta() | shift | delta = 0, allow true | BVA | unchanged |
| testShiftPositiveDeltaAllow() | shift | delta > 0, allow true | Weak ECP | both bounds increase |
| testShiftNegativeDeltaAllow() | shift | delta < 0, allow true | Weak ECP | both bounds decrease |
| testShiftMaxValueAllowZeroCross() | shift | delta = double.max_value | BVA | both bounds = max_value |
| testShiftNegativeAllowZeroCross() | shift | crossing zero, allow false | Strong ECP | Shift of either end allowed if not zero crossing |
| testToStringNormalRange() | toString | normal range | Weak ECP | correct formatted string |
| testToStringNegativeRange() | toString | negative bounds | BVA | correct formatted string |
| testToStringZeroLength() | toString | lower=upper | BVA | correct formatted string |
| testCombineBothNull() | combine | r1=null, r2=null | Robustness | null |
| testCombineFirstNull() | combine | r1=null | Robustness | returns r2 |
| testCombineSecondNull() | combine | r2=null | Robustness | returns r1 |
| testCombineOverlapping() | combine | overlapping ranges | Strong ECP | correct new range |
| testCombineDisjoint() | combine | disjoint | Strong ECP | correct new range |
| testCombineContained() | combine | one contained in other | Strong ECP | correct new range |
| testContainsValueInside() | contains | lower < value < upper | Weak ECP | true |
| testContainsLowerBoundary() | contains | value = lower | BVA | true |
| testContainsUpperBoundary() | contains | value = upper | BVA | true |
| testContainsBelowLower() | contains | value < lower | Weak ECP | false |
| testContainsAboveUpper() | contains | value > upper | Weak ECP | false |
| testGetLengthPositiveRange() | getLength | upper > lower | Weak ECP | upper - lower |
| testGetLengthZeroLength() | getLength | lower = upper | Boundary | 0 |
| testGetLengthNegativeBounds() | getLength | negative bounds | Weak ECP | correct positive length |

# Table 4: DataUtilities Class Test Cases

| Test Method Name | Source Method | Partition Covered | Technique Used | Expected Outcome |
|-----------------|---------------|-----------------|----------------|----------------|
| testCalculateColumnTotalNullDataset() | calculateColumnTotal | data = null | Robustness | NullPointerException |
| testCalculateColumnTotalAllNullCells() | calculateColumnTotal | 1 valid, 1 null cell | Weak ECP | Null treated as 0 |
| testCalculateColumnTotalEmptyTable() | calculateColumnTotal | empty table | BVA | returns 0 |
| testCalculateColumnTotalSingleRow() | calculateColumnTotal | 1 row, valid col | BVA | correct sum |
| testCalculateColumnTotalMultipleRows() | calculateColumnTotal | >1 rows, valid col | Weak ECP | correct sum |
| testCalculateColumnTotalNegativeColumn() | calculateColumnTotal | col < 0 | Robustness | IndexOutOfBoundsException |
| testCalculateColumnTotalTooLargeMocked() | calculateColumnTotal | col >= colCount | BVA + Robustness | IndexOutOfBoundsException |
| testCalculateRowTotalNullDataset() | calculateRowTotal | data = null | Robustness | NullPointerException |
| testCalculateRowTotalAllNullCells() | calculateRowTotal | 1 valid, 1 null cell | Weak ECP | Null treated as 0 |
| testCalculateRowTotalEmptyTable() | calculateRowTotal | empty table | BVA | returns 0 |
| testCalculateRowTotalSingleColumn() | calculateRowTotal | 1 column | BVA | correct sum |
| testCalculateRowTotalMultipleColumns() | calculateRowTotal | >1 columns | Weak ECP | correct sum |
| testCalculateRowTotalNegativeRow() | calculateRowTotal | row < 0 | Robustness | IndexOutOfBoundsException |
| testCalculateRowTotalRowTooLarge() | calculateRowTotal | row ≥ rowCount | BVA + Robustness | IllegalArgumentException |
| testCreateNumberArrayNull() | createNumberArray | data = null | Robustness | IllegalArgumentException |
| testCreateNumberArrayEmpty() | createNumberArray | empty array | BVA | empty Number[] |
| testCreateNumberArraySingleValue() | createNumberArray | single element | BVA | correct conversion |
| testCreateNumberArrayMultipleValues() | createNumberArray | multiple elements | Weak ECP | correct conversion |
| createNumberArray2D_nullInput() | createNumberArray2D | data = null | Robustness | IllegalArgumentException |
| createNumberArray2D_emptyInnerArray() | createNumberArray2D | empty 2D array | BVA | empty Number[][] |
| createNumberArray2D_emptyOuterArray() | createNumberArray2D | Empty outer array | BVA | empty Number[][] |
| createNumberArray2D_singleElement() | createNumberArray2D | 1 row, 1 col | Weak ECP | correct conversion |
| createNumberArray2D_negativeAndZeroValues() | createNumberArray2D | >1 row, >1 col | Weak ECP | correct conversion |
| getCumulativePercentages_nullInput() | getCumulativePercentages | data = null | Robustness | IllegalArgumentException |
| getCumulativePercentages_emptyDataset() | getCumulativePercentages | empty dataset | BVA | empty result |
| getCumulativePercentages_singleValue() | getCumulativePercentages | 1 value | BVA | 100% cumulative |
| getCumulativePercentages_multiplePositiveValues() | getCumulativePercentages | multiple values | Weak ECP | increasing cumulative |
| getCumulativePercentages_multipleZeroValues() | getCumulativePercentages | multiple zero values only | Weak ECP | NaN |
| getCumulativePercentages_mixedValues() | getCumulativePercentages | positive + negative values mix | Strong ECP | correct cumulative calculation |
| getCumulativePercentages_zeroTotalSum() | getCumulativePercentages | total sum = 0, non zero values | Special case | Infinity or NaN |

# 4 How the team work/effort was divided and managed

The team decided as a group which five methods in the Range class would cover the most functionalities and result in the most comprehensive testing plan. Then, given we have ten methods to test, two group members completed all the test cases for three methods and two group members did the remaining two methods. Then each team member reviewed another team member’s test cases.

# 5 Difficulties encountered, challenges overcome, and lessons learned

One of the difficulties during this lab was encountering errors while running the JUnit test cases that were not due to a fault in the test code, but a fault in the SUT. It was not immediately clear which of the two was the actual cause initially. The SUT, as stated in the lab manual, contains a variety of random issues. Specifically, testing the combine() function in the Range class resulted in runtime errors even though the test case was completed with valid inputs. To produce a failure and not an error (as per the lab instructions), another assertion and exception handler had to be included.

The errors also continued when doing null input testing where it was found that the provided documentation stated an InvalidParameterException was expected to be thrown, but the actual error showed an IllegalArgumentException was thrown which made the test neither pass of fail but produce an error in the JUnit report. In the essence of black-box testing, we cannot fully know the internal logic that determines which exception is thrown. As a result, we noted this discrepancy as all our applicable tests check for an IllegalArgumentException to avoid runtime errors and ensure that the tests execute reliably, rather than strictly following the documentation, which appears inaccurate. This way, clearer feedback is produced in the test results, as errors in the test suite can obscure the distinction between actual system failures and mismatches between implementation and specification.

Another challenge with the test implementation was the cascade effect of bugs. If a failure appeared in a simple test, it sometimes caused failures in additional tests designed to test different features. While this is not necessarily a problem with the design, it was more difficult to know if the tests were implemented correctly (from a learning perspective).

# 6 Comments/feedback on the lab itself

One aspect of the assignment that was fairly confusing was how to submit the Eclipse project onto our repo. We were instructed to set the project up with reference jar files but then asked to submit the project without the person running the code having to do anything. The way we understood this, we ended up submitting a complete raw source project folder that internally contains all references. 

Additionally, the Jar provided Javadoc found in index.html caused a point confusion in our group as this didn’t match the Javadoc found when directly searched online or from the general provided reference link in the .md. This made it difficult to know what specification we were meant to design tests against, in particular as seen from the aforementioned exception error discussed earlier. The provided Javadoc is also named modified which may suggest these differences are intentional, however the specific change isn’t clarified causing the confusion. A stricter source/reference and instructions in this regard may have made this less cumbersome to navigate.

