# Solution for Day 24

require_relative "../utils"
require "pry"

# Read input lines
lines = read_input_lines("day_24/input.txt")

components = []

lines.each do |line|
  components << line.split("/").map(&:to_i)
end

def find_strongest_bridge(components, current_port, current_strength)
  possible_next_components = components.select do |component|
    component.include?(current_port)
  end

  if possible_next_components.empty?
    return current_strength
  end

  possible_next_components.map do |component|
    next_port = component[0] == current_port ? component[1] : component[0]
    new_components = components.dup
    new_components.delete(component)
    find_strongest_bridge(new_components, next_port, current_strength + component[0] + component[1])
  end.max
end

def find_longest_bridge(components, current_port, current_strength, current_length)
  possible_next_components = components.select do |component|
    component.include?(current_port)
  end

  if possible_next_components.empty?
    return current_length, current_strength
  end

  final_results = possible_next_components.map do |component|
    next_port = component[0] == current_port ? component[1] : component[0]
    new_components = components.dup
    new_components.delete(component)
    find_longest_bridge(new_components, next_port, current_strength + component[0] + component[1], current_length + 1)
  end

  # Find the x,y, with the max of the lengths
  # If there are multiple, find the max of the strengths
  max_length = 0
  max_strength = 0
  final_results.each do |length, strength|
    if length > max_length
      max_length = length
      max_strength = strength
    elsif length == max_length
      max_strength = [max_strength, strength].max
    end
  end

  return max_length, max_strength
end

puts find_longest_bridge(components, 0, 0, 0)[1]
