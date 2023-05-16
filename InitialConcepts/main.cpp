//Pedro Henrique Masteguin - 158471 - Implementação de conceitos de orientação a objetos

#include <iostream>
#include <string>
#include <cstdlib>
#include <ctime>

using namespace std;

class CodeGenerator {
private:
    string prefix;

public:

    CodeGenerator() : prefix("TXT-"){}
    string getPrefix() const {
        return prefix;
    }

    void setPrefix(const string& newPrefix) {
        prefix = newPrefix;
    }

    virtual string generate() = 0;
};

class RandomNumberCodeGenerator : public CodeGenerator {
public:
    string generate() {
        srand(time(nullptr));
        int number = rand() % 100000 + 1;
        return getPrefix() + to_string(number);
    }
};

int main() {
    CodeGenerator* generator = new RandomNumberCodeGenerator();

    cout << "Generated code: " << generator->generate() << endl;

    generator->setPrefix("EX32-");

    cout << "New generated code: " << generator->generate() << endl;
  
    return 0;
}