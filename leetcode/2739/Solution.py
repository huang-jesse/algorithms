class Solution:
    def distanceTraveled(self, mainTank: int, additionalTank: int) -> int:
        consumeFuel = 0
        while mainTank >= 5:
            mainTank -= 5
            consumeFuel += 5
            if additionalTank > 0:
                additionalTank -= 1
                mainTank += 1
        consumeFuel += mainTank
        mainTank = 0
        return consumeFuel * 10

if __name__ == '__main__':
    sol = Solution()
    mainTank = 5
    additionalTank = 10
    print("test:", sol.distanceTraveled(mainTank, additionalTank))