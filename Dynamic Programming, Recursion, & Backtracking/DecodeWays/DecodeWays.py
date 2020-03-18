'''
    This repository is no longer actively maintained. To find all solutions
    to this problem (and practice coding more problems) at:

    ~~~~~~~~~~~~~~~~~~~~~~~~~
    https://backtobackswe.com
    ~~~~~~~~~~~~~~~~~~~~~~~~~
'''

class Solution(object):
    def numDecodings(self, s):
        """
        :type s: str
        :rtype: int
        """
        
        dp = [-1] * len(s)
        
        def recur(s, decode_pointer):
            if decode_pointer >= len(s):
                return 1
            
            if dp[decode_pointer] > -1:
                return dp[decode_pointer]

            answer = 0
            for i in range(1,3):
                if decode_pointer + i <= len(s):
                    substr = s[decode_pointer:decode_pointer + i]
                    if int(substr) >= 1 and int(substr) <= 26 and not substr.find('0') == 0:
                        answer += recur(s, decode_pointer+i)

            dp[decode_pointer] = answer
            return dp[decode_pointer]
        
        return recur(s, 0)