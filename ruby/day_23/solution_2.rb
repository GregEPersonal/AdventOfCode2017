# Solution for Day 23

require_relative "../utils"
require "pry"

# Read input lines
lines = read_input_lines("day_23/input.txt")

instructions = []

lines.each do |line|
  instruction, register, value = line.split(" ")
  instructions << [instruction, register.to_sym, value]
end

components = []
registers = {
  a: 1,
  b: 0,
  c: 0,
  d: 0,
  e: 0,
  f: 0,
  g: 0,
  h: 0,
}

def process_value(string_value, registers)
  if string_value.to_i.to_s == string_value
    string_value.to_i
  else
    registers[string_value.to_sym]
  end
end

# set X Y sets register X to the value of Y.
# sub X Y decreases register X by the value of Y.
# mul X Y sets register X to the result of multiplying the value contained in register X by the value of Y.
# jnz X Y jumps with an offset of the value of Y, but only if the value of X is not zero.
# (An offset of 2 skips the next instruction, an offset of -1 jumps to the previous instruction, and so on.)
def process_instruction(instruction_array, registers, current_instruction_index)
  instruction, register, string_value = instruction_array
  value = process_value(string_value, registers)

  case instruction
  when "set"
    registers[register] = value.to_i
  when "sub"
    registers[register] -= value.to_i
  when "mul"
    registers[register] *= value.to_i
  when "jnz"
    if registers[register] != 0
      current_instruction_index += value.to_i - 1
    end
  end

  current_instruction_index + 1
end

def use_checkpoint_1(registers)
  registers[:a] = 1
  registers[:b] = 106700
  registers[:c] = 123700
  registers[:d] = 6
  registers[:e] = 106700
  registers[:f] = 0
  registers[:g] = 0
  registers[:h] = 0

  return 19
end

current_instruction_index = 0
instruction_count = 0
mul_count = 0

# Shortcut to pass the first loop
# current_instruction_index = use_checkpoint_1(registers)

# while current_instruction_index >= 0 && current_instruction_index < instructions.length
#   if instruction_count % 1000 == 0
#     puts instruction_count
#     puts registers
#     puts current_instruction_index
#     puts instructions[current_instruction_index]
#     puts
#   end
#   current_instruction = instructions[current_instruction_index]
#   current_instruction_command = current_instruction[0]
#   current_instruction_index = process_instruction(instructions[current_instruction_index], registers, current_instruction_index)

#   mul_count += 1 if current_instruction_command == "mul"
#   instruction_count += 1
# end

# puts mul_count

#  On evaluation of the program in excel, it appears it roughly loops from
#  106700 to 123700 in increments of 17.
#  If the number is NOT prime, it increments h by 1.

def is_prime(number)
  return false if number < 2

  (2..Math.sqrt(number)).each do |i|
    return false if number % i == 0
  end

  true
end

h = 0
b = 106700
c = 123700
while (b <= c)
  h += 1 if !is_prime(b)

  b += 17
end

puts h
