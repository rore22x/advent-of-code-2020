package de.romrei.aoc

import java.util.*

class Day4 {


    fun part1(pwdCollections: List<PwdCollection>) {
        println("result: ${pwdCollections.filter { it.containsAll() }.count()}")
    }

    fun part2(pwdCollections: List<PwdCollection>) {
        println("result: ${pwdCollections.filter { it.containsAllValid() }.count()}")
    }

    class PwdCollection(val pwds: Set<Pwd>) {

        fun containsAll(): Boolean {
            for (token in MUST_HAVE) {
                if (!pwds.any { it.key == token }) {
                    return false
                }
            }
            return true
        }

        fun containsAllValid(): Boolean {
            for (token in MUST_HAVE) {
                if (!pwds.any { it.key == token && it.valid }) {
                    return false
                }
            }
            return true
        }

        override fun toString(): String {
            return "$pwds"
        }

    }

    enum class Entry(val validator: (String) -> Boolean) {
        byr({ it.length == 4 && it.toInt() >= 1920 && it.toInt() <= 2002 }),
        iyr({ it.length == 4 && it.toInt() >= 2010 && it.toInt() <= 2020 }),
        eyr({ it.length == 4 && it.toInt() >= 2020 && it.toInt() <= 2030 }),
        hgt({
            var result = false
            if (Regex("^[0-9]+((cm)|(in))$").matches(it)) {
                val isCm = it.endsWith("cm")
                result = if (isCm) {
                    val num = it.replace("cm", "")
                    num.toInt() in 150..193
                } else {
                    val num = it.replace("in", "")
                    num.toInt() in 59..76
                }
            }
            result
        }),

        hcl({ it.matches(Regex("^#[a-f0-9]{6}$")) }), // a # followed by exactly six characters 0-9 or a-f.
        ecl({ it.matches(Regex("^((amb)|(blu)|(brn)|(gry)|(grn)|(hzl)|(oth))$")) }), //amb blu brn gry grn hzl oth.
        pid({ it.matches(Regex("^[0-9]{9}$")) }),
        cid({ true });

        companion object {
            fun resolve(name: String): Entry? {
                return Arrays.stream(values()).filter { it.name == name }.findFirst().orElse(null)
            }
        }
    }

    class Pwd(val key: String, val value: String) {
        private val resolved = Entry.resolve(key)
        val valid = resolved!!.validator(value)

        override fun equals(other: Any?): Boolean {
            if (this === other) return true
            if (other !is Pwd) return false

            if (key != other.key) return false
            if (value != other.value) return false

            return true
        }

        override fun hashCode(): Int {
            var result = key.hashCode()
            result = 31 * result + value.hashCode()
            return result
        }

        override fun toString(): String {
            return "(r=${if (valid) "t" else "f"}, k='$key', v='$value')"
        }


    }

    companion object {
        val MUST_HAVE = setOf("byr", "iyr", "eyr", "hgt", "hcl", "ecl", "pid")

        fun parse(text: String): List<PwdCollection> {
            val pwds = text.split("\n\n")
            val colls = mutableListOf<PwdCollection>()
            pwds.forEach {
                val actualLine = it.replace("\n", " ")
                val keyValues = actualLine.split(" ")
                val keyvalueset = mutableSetOf<Pwd>()
                keyValues.forEach { keyValue ->
                    val keyAndValue = keyValue.split(":")
                    if (keyAndValue.size == 2) {
                        val pwd = Pwd(keyAndValue[0], keyAndValue[1])
                        keyvalueset.add(pwd)
                    }
                }
                colls.add(PwdCollection(keyvalueset))
            }
            return colls
        }

    }

}