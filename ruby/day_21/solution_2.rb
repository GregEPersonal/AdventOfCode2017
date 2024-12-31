# Solution for Day 21

require_relative "../utils"
require "pry"

# Read input lines
lines = read_input_lines("day_21/input.txt")

starting_pattern = ".#./..#/###"
starting_pattern_2d_array = starting_pattern.split("/").map { |row| row.split("") }

puts "Starting pattern:"
puts starting_pattern_2d_array

def parse_rule_to_array(rule_string)
  rule_string.split("/").map { |row| row.split("") }
end

# Rotate an array, 90 degrees clockwise
def rotate_array(rule_array)
  if rule_array.length == 2
    return [
             [rule_array[1][0], rule_array[0][0]],
             [rule_array[1][1], rule_array[0][1]],
           ]
  elsif rule_array.length == 3
    return [
             [rule_array[2][0], rule_array[1][0], rule_array[0][0]],
             [rule_array[2][1], rule_array[1][1], rule_array[0][1]],
             [rule_array[2][2], rule_array[1][2], rule_array[0][2]],
           ]
  end
end

def flip_array_horizontal(rule_array)
  rule_array.map { |row| row.reverse }
end

def flip_array_vertical(rule_array)
  rule_array.reverse
end

rules_hash = {}

# Example rules
#   ../.# => ##./#../...
#   .#./..#/### => #..#/..../..../#..#
lines.each do |line|
  starting_string, ending_string = line.split(" => ")
  start_pattern = parse_rule_to_array(starting_string)
  end_pattern = parse_rule_to_array(ending_string)

  # Add the rule to the hash
  # Also add each of the rotations, and then also the flips AND their rotations. That's 12 total.
  rules_hash[start_pattern] = end_pattern
  rules_hash[rotate_array(start_pattern)] = end_pattern
  rules_hash[rotate_array(rotate_array(start_pattern))] = end_pattern
  rules_hash[rotate_array(rotate_array(rotate_array(start_pattern)))] = end_pattern
  rules_hash[flip_array_horizontal(start_pattern)] = end_pattern
  rules_hash[rotate_array(flip_array_horizontal(start_pattern))] = end_pattern
  rules_hash[rotate_array(rotate_array(flip_array_horizontal(start_pattern)))] = end_pattern
  rules_hash[rotate_array(rotate_array(rotate_array(flip_array_horizontal(start_pattern))))] = end_pattern
  rules_hash[flip_array_vertical(start_pattern)] = end_pattern
  rules_hash[rotate_array(flip_array_vertical(start_pattern))] = end_pattern
  rules_hash[rotate_array(rotate_array(flip_array_vertical(start_pattern)))] = end_pattern
  rules_hash[rotate_array(rotate_array(rotate_array(flip_array_vertical(start_pattern))))] = end_pattern
end

def process_mod2_array(array, rules_hash)
  array_size = array.length
  new_size = array_size / 2 * 3
  new_array = Array.new(new_size) { Array.new(new_size) }
  # Iterate through the array in 2x2 squares
  (0...array_size / 2).each do |i|
    (0...array_size / 2).each do |j|
      # Get just the 2x2 square
      square = [
        [array[i * 2][j * 2], array[i * 2][j * 2 + 1]],
        [array[i * 2 + 1][j * 2], array[i * 2 + 1][j * 2 + 1]],
      ]
      new_square = rules_hash[square]
      # Place the new square into the new array
      (0...3).each do |k|
        (0...3).each do |l|
          new_array[i * 3 + k][j * 3 + l] = new_square[k][l]
        end
      end
    end
  end
  new_array
end

def print_grid(array)
  array.each do |row|
    puts row.join("")
  end
end

def count_on_pixels(array)
  array.flatten.count("#")
end

def process_mod3_array(array, rules_hash)
  array_size = array.length
  new_size = array_size / 3 * 4
  new_array = Array.new(new_size) { Array.new(new_size) }
  # Iterate through the array in 2x2 squares
  (0...array_size / 3).each do |i|
    (0...array_size / 3).each do |j|
      # Get just the 3x3 square
      square = [
        [array[i * 3][j * 3], array[i * 3][j * 3 + 1], array[i * 3][j * 3 + 2]],
        [array[i * 3 + 1][j * 3], array[i * 3 + 1][j * 3 + 1], array[i * 3 + 1][j * 3 + 2]],
        [array[i * 3 + 2][j * 3], array[i * 3 + 2][j * 3 + 1], array[i * 3 + 2][j * 3 + 2]],
      ]
      new_square = rules_hash[square]
      # Place the new square into the new array
      (0...4).each do |k|
        (0...4).each do |l|
          new_array[i * 4 + k][j * 4 + l] = new_square[k][l]
        end
      end
    end
  end
  new_array
end

total_iterations = 18
current_array = starting_pattern_2d_array

(0...total_iterations).each do |iteration|
  array_size = current_array.length

  # If the array size is divisible by 2, then we need to break it into 2x2 squares
  if array_size % 2 == 0
    current_array = process_mod2_array(current_array, rules_hash)
  elsif array_size % 3 == 0
    current_array = process_mod3_array(current_array, rules_hash)
  end
end

puts "Final array:"
print_grid(current_array)
puts "Number of on pixels: #{count_on_pixels(current_array)}"
