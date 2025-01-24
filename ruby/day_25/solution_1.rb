# Solution for Day 24

require_relative "../utils"
require "pry"

# Read input lines
lines = read_input_lines("day_25/input.txt")

step_goal = lines[1].split(" ")[5].to_i
puts "Step goal: #{step_goal}"
# Of lines 3 through the end, split them into chunks of 10
# Each chunk is a group of 10 lines that represent a single state
# Example:
# In state A:
# If the current value is 0:
# - Write the value 1.
# - Move one slot to the right.
# - Continue with state B.
# If the current value is 1:
# - Write the value 0.
# - Move one slot to the left.
# - Continue with state C.
states = {}
lines[2..-1].each_slice(10).each do |chunk|
  state_name = chunk[1].split(" ")[2].chop
  states[state_name] = {}
  chunk[2..-1].each_slice(4).each do |rule_string|
    current_value = rule_string[0].split(" ").last.chop.to_i
    states[state_name][current_value] = {}
    states[state_name][current_value][:new_value] = rule_string[1].split(" ").last.chop.to_i
    states[state_name][current_value][:direction] = rule_string[2].split(" ").last.chop
    states[state_name][current_value][:new_state] = rule_string[3].split(" ").last.chop
  end
end

def get_new_cursor_from_direction(direction, cursor)
  if direction == "right"
    return cursor += 1
  else
    return cursor -= 1
  end
end

def process_step(tape, cursor, states, state)
  current_state = states[state]
  current_value = tape[cursor] || 0

  new_value = current_state[current_value][:new_value]
  new_state = current_state[current_value][:new_state]
  new_cursor = get_new_cursor_from_direction(current_state[current_value][:direction], cursor)

  tape[cursor] = new_value

  return new_cursor, new_state
end

tape = { 0 => 0 }
cursor = 0
state = "A"
step = 0

while step < step_goal
  if step % 10000 == 0
    puts "Step: #{step}"
  end
  cursor, state = process_step(tape, cursor, states, state)

  step += 1
end

puts tape.values.count(1)
