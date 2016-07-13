package org.hcjf.layers.crud;

import org.hcjf.layers.LayerInterface;
import org.hcjf.layers.Query;

import java.util.List;
import java.util.Map;

/**
 * @author javaito
 * @mail javaito@gmail.com
 */
public interface CrudLayerInterface<O extends Object> extends LayerInterface {

    /**
     * This method return the resource class of the layer.
     * @return Resource class.
     */
    public Class<O> getResourceType();

    /**
     * This method implements the creation of the resource.
     * @param object Object to represents an instance of the resource.
     * @param parameters Some parameters that could be necessaries
     *                   in order to create an instance of the resource.
     * @return The instance of the resource.
     */
    public O create(O object, Map<String, Object> parameters);

    /**
     * This method implements the read operation to find an instance of
     * the resource using only it's id.
     * @param id Id to found the instance.
     * @return Return the instance founded or null if the instance is not found.
     */
    public O read(Object id);

    /**
     * This method implements the update of the resource.
     * @param object Instance of the resource that gonna be updated.
     *               This instance must have an id to identify the updatable data.
     * @param parameters Some parameters that could be necessaries
     *                   in order to update an instance of the resource.
     * @return The instance updated.
     */
    public O update(O object, Map<String, Object> parameters);

    /**
     * This method implements the delete operation over the resource.
     * @param id Id of the instance that gonna be deleted.
     * @return Instance of the resource that was deleted.
     */
    public O delete(Object id);

    /**
     * This method implements the read operation without filters.
     * @return List with all the instances of the resource.
     */
    public List<O> read();

    /**
     * This method implements the read operation using the filters
     * specified in the query.
     * @param queryId Id of the query.
     * @return Return the list with the instances founded.
     */
    public List<O> read(Query.QueryId queryId);

    /**
     * This method implements the update operation over a set of the instances,
     * this instances are selected using the query like a filter.
     * @param queryId Id of the query.
     * @param parameters Values that contains the information to update the
     *                   instances.
     * @return Return the instances updated.
     */
    public List<O> update(Query.QueryId queryId, Map<String, Object> parameters);

    /**
     * This method implements the delete operation over a set of the instances.
     * This instances are selected using the query like a filter.
     * @param queryId Id of the query.
     * @return Return the instances deleted.
     */
    public List<O> delete(Query.QueryId queryId);

    /**
     * This method implements the read operation over the set of queries created.
     * @param queryId Id of the query.
     * @return Return the instance of the resource's query.
     */
    public Query readQuery(Query.QueryId queryId);

    /**
     * This method implements the create operation of the resource's query.
     * @param parameters Some parameters that could be necessaries
     *                   in order to create an instance of the resource's query.
     * @return Instance of the resource's query.
     */
    public Query createQuery(Query query, Map<String, Object> parameters);

    /**
     * This method implements the update operation of the resource's query.
     * @param query Instance of the resource's query that gonna be updated.
     *              This instance must have an id to identify the updatable data.
     * @param parameters Some parameters that could be necessaries
     *                   in order to update an instance of the resource's query.
     * @return Updated instance of the resource's query.
     */
    public Query updateQuery(Query query, Map<String, Object> parameters);

    /**
     * This method implements the delete operation of the resource's query.
     * @param queryId Id of the query.
     * @return Deleted instance of the resource's query.
     */
    public Query deleteQuery(Query.QueryId queryId);
}
