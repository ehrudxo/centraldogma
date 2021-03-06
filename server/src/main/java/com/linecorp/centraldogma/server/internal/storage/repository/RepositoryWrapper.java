/*
 * Copyright 2017 LINE Corporation
 *
 * LINE Corporation licenses this file to you under the Apache License,
 * version 2.0 (the "License"); you may not use this file except in compliance
 * with the License. You may obtain a copy of the License at:
 *
 *   https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations
 * under the License.
 */

package com.linecorp.centraldogma.server.internal.storage.repository;

import static java.util.Objects.requireNonNull;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.CompletableFuture;

import com.linecorp.centraldogma.common.Author;
import com.linecorp.centraldogma.common.Change;
import com.linecorp.centraldogma.common.Commit;
import com.linecorp.centraldogma.common.Entry;
import com.linecorp.centraldogma.common.Markup;
import com.linecorp.centraldogma.common.Query;
import com.linecorp.centraldogma.common.QueryResult;
import com.linecorp.centraldogma.common.Revision;
import com.linecorp.centraldogma.internal.Util;
import com.linecorp.centraldogma.server.internal.storage.project.Project;

public class RepositoryWrapper implements Repository {

    private final Repository repo;

    protected RepositoryWrapper(Repository repo) {
        this.repo = requireNonNull(repo, "repo");
    }

    @SuppressWarnings("unchecked")
    public final <T extends Repository> T unwrap() {
        return (T) repo;
    }

    @Override
    public Project parent() {
        return unwrap().parent();
    }

    @Override
    public String name() {
        return unwrap().name();
    }

    @Override
    public CompletableFuture<Revision> normalize(Revision revision) {
        return unwrap().normalize(revision);
    }

    @Override
    public CompletableFuture<Boolean> exists(Revision revision, String path) {
        return unwrap().exists(revision, path);
    }

    @Override
    public CompletableFuture<Entry<?>> get(Revision revision, String path) {
        return unwrap().get(revision, path);
    }

    @Override
    public <T> CompletableFuture<QueryResult<T>> get(Revision revision, Query<T> query) {
        return unwrap().get(revision, query);
    }

    @Override
    public CompletableFuture<Entry<?>> getOrElse(Revision revision, String path, Entry<?> other) {
        return unwrap().getOrElse(revision, path, other);
    }

    @Override
    public <T> CompletableFuture<QueryResult<T>> getOrElse(Revision revision,
                                                           Query<T> query, QueryResult<T> other) {
        return unwrap().getOrElse(revision, query, other);
    }

    @Override
    public CompletableFuture<Map<String, Entry<?>>> find(Revision revision, String pathPattern) {
        return unwrap().find(revision, pathPattern);
    }

    @Override
    public CompletableFuture<Map<String, Entry<?>>> find(Revision revision, String pathPattern,
                                                         Map<FindOption<?>, ?> options) {
        return unwrap().find(revision, pathPattern, options);
    }

    @Override
    public CompletableFuture<List<Commit>> history(Revision from, Revision to, String pathPattern) {
        return unwrap().history(from, to, pathPattern);
    }

    @Override
    public CompletableFuture<List<Commit>> history(Revision from, Revision to,
                                                   String pathPattern, int maxCommits) {
        return unwrap().history(from, to, pathPattern, maxCommits);
    }

    @Override
    public CompletableFuture<Change<?>> diff(Revision from, Revision to, Query<?> query) {
        return unwrap().diff(from, to, query);
    }

    @Override
    public CompletableFuture<Map<String, Change<?>>> diff(Revision from, Revision to, String pathPattern) {
        return unwrap().diff(from, to, pathPattern);
    }

    @Override
    public CompletableFuture<Map<String, Change<?>>> previewDiff(Revision baseRevision,
                                                                 Iterable<Change<?>> changes) {
        return unwrap().previewDiff(baseRevision, changes);
    }

    @Override
    public CompletableFuture<Map<String, Change<?>>> previewDiff(Revision baseRevision, Change<?>... changes) {
        return unwrap().previewDiff(baseRevision, changes);
    }

    @Override
    public CompletableFuture<Revision> commit(Revision baseRevision, Author author,
                                              String summary, Iterable<Change<?>> changes) {
        return unwrap().commit(baseRevision, author, summary, changes);
    }

    @Override
    public CompletableFuture<Revision> commit(Revision baseRevision, Author author,
                                              String summary, Change<?>... changes) {
        return unwrap().commit(baseRevision, author, summary, changes);
    }

    @Override
    public CompletableFuture<Revision> commit(Revision baseRevision, Author author,
                                              String summary, String detail, Markup markup,
                                              Iterable<Change<?>> changes) {
        return unwrap().commit(baseRevision, author, summary, detail, markup, changes);
    }

    @Override
    public CompletableFuture<Revision> commit(Revision baseRevision, Author author,
                                              String summary, String detail, Markup markup,
                                              Change<?>... changes) {
        return unwrap().commit(baseRevision, author, summary, detail, markup, changes);
    }

    @Override
    public CompletableFuture<Revision> watch(Revision lastKnownRev, String pathPattern) {
        return unwrap().watch(lastKnownRev, pathPattern);
    }

    @Override
    public <T> CompletableFuture<QueryResult<T>> watch(Revision lastKnownRev, Query<T> query) {
        return unwrap().watch(lastKnownRev, query);
    }

    @Override
    public CompletableFuture<Revision> createRunspace(Author author, int majorRevision) {
        return unwrap().createRunspace(author, majorRevision);
    }

    @Override
    public CompletableFuture<Void> removeRunspace(int majorRevision) {
        return unwrap().removeRunspace(majorRevision);
    }

    @Override
    public CompletableFuture<Set<Revision>> listRunspaces() {
        return unwrap().listRunspaces();
    }

    @Override
    public String toString() {
        return Util.simpleTypeName(this) + '(' + unwrap() + ')';
    }
}
