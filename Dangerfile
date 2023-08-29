if github.pr_body.length == 0
    fail "Please provide a summary in the Pull Request description."
end

if git.lines_of_code > 500
    warn "Please consider breaking up this pull request."
end

if git.deletions > git.insertions
    message  "🎉 Code Cleanup!"
end
