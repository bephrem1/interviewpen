'''
  This repository is no longer actively maintained. To find all solutions
  to this problem (and practice coding more problems) at:

  ~~~~~~~~~~~~~~~~~~~~~~~~~
  https://backtobackswe.com
  ~~~~~~~~~~~~~~~~~~~~~~~~~
'''

class BalancedParens:
  def checkParens(s):
    '''
    Checks s for balanced parentheses.
    Assume that s is a string that has only open or closed parentheses.
    '''
    if not s:
      return True

    left = 0

    for char in s:
      if char == ')':
        if left == 0:
          return False

        left -= 1

      else:
        left += 1

    return left == 0

  def checkPairs(s):
    '''
    Checks s for balanced parentheses, brackets, braces.
    Assume that s only consists of characters in the `mapping` variable below.
    '''
    if not s:
      return True

    # Mapping is used to map closed characters to their opening match
    mapping = {
      ')': '(',
      '}': '{',
      ']': '['
    }

    # We use a list to represent a stack of all the unmatched open characters
    # so far. This varies by language; for example, in Java, we'd use a Stack 
    # type instead.
    stk = []
    for char in s:
      if char in mapping:
        if not stk or stk.pop() != mapping[char]:
          return False

      else:
        stk.append(char)

    return not stk

def testBalancedParens():
  testParens = [
    # valid parens
    '()', '()()', '(()())', '((()))', 
    # invalid parens
    '(', ')', '(()', '())', ')()(']

  testPairs = [
    # valid pairs
    '([{}])', '[{}{}(){}{}]', '{()[()]}',
    # invalid pairs
    '([)]', '{{]]', '{[([})', '{[', ')]']

  print('Testing parentheses')
  print()

  for parens in testParens:
    checkParens = 'Valid' if BalancedParens.checkParens(parens) else 'Invalid'
    print('{}: {}'.format(parens, checkParens))

  print()
  print('Testing pairs')
  print()

  for pairs in testPairs:
    checkPairs = 'Valid' if BalancedParens.checkPairs(pairs) else 'Invalid'
    print('{}: {}'.format(pairs, checkPairs))

if __name__ == '__main__':
  testBalancedParens()
